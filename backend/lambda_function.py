import json
import boto3

dynamodb = boto3.resource('dynamodb')
table = dynamodb.Table('emmanueldb')

#The oly item my API is allowed to touch (for input validation purposes. 1st security fix)
ALLOWED_COUNTER_ID = 'visitCount'


def build_response(status_code, body):
    """Build the response format API Gateway expects (DRY: written once)."""
    return {
        'statusCode': status_code,
        'headers': {
            #CORS is now handled by API Gateway itself (no need for custom headers)
            'Content-Type': 'application/json'
        },
        'body': json.dumps(body)
    }


def lambda_handler(event, context):
    try:
        #1. Defensive: .get() returns Non instead of crashing if body is missing
        raw_body =  event.get('body')
        if not raw_body:
            return build_response(400, {'error': 'Request body is required'})

        parsed_body = json.loads(raw_body)

        #2. Validation: the JSON must be an object, and counterID must be the allowed one
        if not isinstance(parsed_body, dict):
            return build_response(400, {'error': 'Request body must be a JSON object'})
        
        counter_id = parsed_body.get('counterId')
        if counter_id != ALLOWED_COUNTER_ID:
            return build_response(400, {'error': 'Invalid counterId'})

        #Atomic increment: DynamoDB does the read and the +1 in a single step
        response = table.update_item (
            Key={'counterId': counter_id},
            UpdateExpression='SET #counter = if_not_exists(#counter, :start) + :inc',
            ExpressionAttributeNames={
                '#counter': 'siteVisits'
            },
            ExpressionAttributeValues={
                ':start': 0,
                ':inc': 1
            },
            ReturnValues='UPDATED_NEW'
        )
        
        return build_response(200, {'siteVisits': int(response['Attributes']['siteVisits'])})

    except json.JSONDecodeError:
        return build_response(400, {'error': 'Invalid JSON format'})

    except Exception as e:
        #3.Log the real error privately in cloudwatch, then return a generic message to prevent errors from leaking back to callers.
        print(f"Unexpected error: {type(e).__name__}: {e}")
        return build_response(500, {'error': 'Internal server error'})