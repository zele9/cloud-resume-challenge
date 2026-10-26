let navOpen = document.querySelector("#second-part-img");
let navClose = document.querySelector("#close-menu");
let navMenu = document.querySelector(".mobile-navbar-right");

// Open the navigation menu
navOpen.addEventListener('click', () => {
    navMenu.style.right = '0px';
});
// Close the nav menu
 // Set the right position of the nav menu to -350px
navClose.addEventListener('click', () => {
    navMenu.style.right = '-500px';
})

const typed_1 = new Typed(".typewrite_1", {
    strings: ["AWS Solutions Architect", "Software Developer", "Plane Enthusiast"],  // strings to be displayed in the typewriter effect (array)
    typeSpeed: 150,
    backSpeed: 150,
    loop: true
})

const typed_2 = new Typed(".typewrite_2", {
    strings: ["AWS Solutions Architect", "Software Developer", "Plane Enthusiast"],  // strings to be displayed in the typewriter effect (array)
    typeSpeed: 150,
    backSpeed: 150,
    loop: true
})

const schoolsBtn = document.querySelector("#schools-btn");
const certsBtn = document.querySelector("#certs-btn");
const showAll = document.querySelector("#all-btn");

const umgc = document.querySelector("#umgc");
const awssaa = document.querySelector("#aws-saa");
const ubuea = document.querySelector("#ubuea");
const awsdev = document.querySelector("#aws-dev");


// Function to display schools
function displaySchools() {
    // Show schools
    umgc.classList.remove("hide-text-card");
    ubuea.classList.remove("hide-text-card");

    // hide-text-card certifications
    awssaa.classList.add("hide-text-card");
    awsdev.classList.add("hide-text-card");
}
schoolsBtn.addEventListener("click", displaySchools);


// Function to display certifications
function displayCertifications() {
    // Remove hide-text-card class certifications
    awssaa.classList.remove("hide-text-card");
    awsdev.classList.remove("hide-text-card");

    // Add hide-text-card class to schools
    umgc.classList.add("hide-text-card");
    ubuea.classList.add("hide-text-card");
}
certsBtn.addEventListener("click", displayCertifications);


// Function to display all education items
function displayAllEducation() {
    // Removing hide-text-card class from umgc
    umgc.classList.remove("hide-text-card");

    // Removing hide-text-card class from ubuea
    ubuea.classList.remove("hide-text-card");

    // Removing hide-text-card class from awssaa
    awssaa.classList.remove("hide-text-card");

    // Removing hide-text-card class from awsdev
    awsdev.classList.remove("hide-text-card");
}
showAll.addEventListener('click', displayAllEducation);


// Wait for the DOM to fully load before executing
document.addEventListener('DOMContentLoaded', () => {
    // Locate the span where the age should appear
    const ageElement = document.getElementById('age');

    // If the span exists, insert the calculated age
    if (ageElement) {
        ageElement.textContent = calculateAge('2000-11-30'); // Use your actual birthdate here
    }
});


// Listen for any scrolling on the window
window.addEventListener('scroll', () => {
    // Get the scroll-to-top arrow icon element
    const arrow = document.getElementById('arrowIcon');

    // If the arrow isn't present, don't do anything
    if (!arrow) return;

    // If the page is scrolled all the way to the top
    if (window.scrollY === 0) {
        // Make the arrow invisible and non-interactive
        arrow.style.opacity = 0;
        arrow.style.pointerEvents = 'none';
    } else {
        // Make the arrow visible and interactive
        arrow.style.opacity = 1;
        arrow.style.pointerEvents = 'auto';
    }
});


// Function to increment the counter and update the displayed value
document.addEventListener('DOMContentLoaded', async () => {
    const visitCounterElement = document.getElementById('visitCounter');

    // Replace with your API Gateway endpoint URL
    const API_ENDPOINT = 'https://yxtm691qng.execute-api.us-east-2.amazonaws.com/developement/emmanuelfunction'; 

    try {
        const dataToSend = {
            'counterId': 'visitCount', // Match the itemId used in your Lambda code   // The amount to increment by
        };

        const response = await fetch(API_ENDPOINT, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(dataToSend)
        });

        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }

        const result = await response.json();

        // Extract the updated value from the response
        const updatedCounter = result.siteVisits; // Adjust based on your Lambda response structure

        // Update the HTML element
        visitCounterElement.textContent = updatedCounter;

        console.log('Update successful:', result); 
    } catch (error) {
        console.error('Error incrementing counter:', error); 
    }
});
