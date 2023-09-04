let inactivityTimer;

function resetInactivityTimer() {
    clearTimeout(inactivityTimer);
    inactivityTimer = setTimeout(logout, 6000); // Logout after 10 minutes of inactivity
}

function logout() {
    // Make an Ajax request to /logout or /logout endpoint
    window.location.href = '/logout';
}

// Reset the timer on user activity
document.addEventListener('mousemove', resetInactivityTimer);
document.addEventListener('keydown', resetInactivityTimer);

// Initialize the timer when the page loads
resetInactivityTimer();
