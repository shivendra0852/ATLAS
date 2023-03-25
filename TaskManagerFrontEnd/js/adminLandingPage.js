// Get all the heading elements
const headings = document.querySelectorAll('.heading');

// Get all the tab elements
const tabs = document.querySelectorAll('.tab');

// Add click event listeners to the heading elements
headings.forEach((heading, index) => {
  heading.addEventListener('click', () => {
    // Get the data-tab attribute value of the clicked heading
    const tabId = heading.dataset.tab;

    // Hide all the tabs
    tabs.forEach(tab => {
      tab.classList.remove('active');
    });

    // Show the clicked tab
    document.getElementById(tabId).classList.add('active');
  });

  // Add submit event listener to the form
  const form = document.querySelectorAll('form')[index];
  const submitBtn = document.querySelectorAll('#submit-btn')[index];
  const textarea = document.querySelectorAll('textarea')[index];
  form.addEventListener('submit', (event) => {
    event.preventDefault();

    // Get the contents of the textarea
    const textareaValue = textarea.value;

    // Do something with the textarea value (e.g. send it to a server-side script)
    console.log(`Textarea value: ${textareaValue}`);
  });
});