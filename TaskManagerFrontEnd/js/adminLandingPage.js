const admin = JSON.parse(localStorage.getItem('adminData'));
let ans = document.createElement("h3");
ans.innerText = admin.name;
document.querySelector("span").append(ans);


const logout = async () => {

  const options = {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ uId: admin.uId }), // include uId in the request body
  };

  const response = await fetch("http://localhost:8808/logoutAdmin", options);

  if (response.ok) {
    // clear admin details from local storage
    localStorage.removeItem("adminData");

    // redirect user to login page
    window.location.href = "login.html";
  } else {
    // handle error
    alert("Logout failed");
  }
};


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

  // // Add submit event listener to the form
  // const form = document.querySelectorAll('form')[index];
  // const submitBtn = document.querySelectorAll('#submit-btn')[index];
  // const textarea = document.querySelectorAll('textarea')[index];
  // form.addEventListener('submit', (event) => {
  //   event.preventDefault();

  //   // Get the contents of the textarea
  //   const textareaValue = textarea.value;

  //   // Do something with the textarea value (e.g. send it to a server-side script)
  //   console.log(`Textarea value: ${textareaValue}`);
  // });

  // Get the form element for the "Get tasks of a sprint" tab
const form5 = document.querySelector('#tab5 form');

// Handle the form submit event
form5.addEventListener('submit', function (event) {
  // Prevent the default form submit behavior
  event.preventDefault();

  // Get the textarea element and its value
  const textarea = document.querySelector('#textbox5');
  const value = textarea.value;

  // Do something with the textarea value
  console.log(value);
});

});


// ===========================================================================================

// Get the submit buttons
const submitBtn1 = document.getElementById("submit-btn");
const submitBtn2 = document.getElementById("submit-btn2");
const submitBtn3 = document.getElementById("submit-btn3");
const submitBtn4 = document.getElementById("submit-btn4");

// Add event listeners to the submit buttons
submitBtn1.addEventListener("click", (event) => {
  event.preventDefault();
  const name = document.getElementById("name").value;
  const startDate = document.getElementById("start-date").value;
  const endDate = document.getElementById("end-date").value;
  const data = {
    name: name,
    startDate: startDate,
    endDate: endDate,
  };
  const options = {
    method: "POST",
    body: JSON.stringify(data),
    headers: {
      "Content-Type": "application/json",
    },
  };
  fetch("http://localhost:8808/sprint", options)
    .then((response) => {
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      return response.json();
    })
    .then((data) => {
      console.log(data);
    })
    .catch((error) => {
      console.error("There was an error!", error);
    });
});

submitBtn2.addEventListener("click", (event) => {
  event.preventDefault();
  const name = document.getElementById("name2").value;
  const description = document.getElementById("desc2").value;
  const taskType = document.getElementById("tasktype2").value;
  const taskStatus = document.getElementById("taskstatus2").value;
  const sprintId = document.getElementById("sprintid2").value;
  const data = {
    name: name,
    description: description,
    taskType: taskType,
    taskStatus: taskStatus,
    sprintId: sprintId,
  };
  const options = {
    method: "POST",
    body: JSON.stringify(data),
    headers: {
      "Content-Type": "application/json",
    },
  };
  const url = `http://localhost:8808/task/${sprintId}`;
  fetch(url, options)
    .then((response) => {
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      return response.json();
    })
    .then((data) => {
      console.log(data);
    })
    .catch((error) => {
      console.error("There was an error!", error);
    });
});

submitBtn3.addEventListener("click", (event) => {
  event.preventDefault();
  const userId = document.getElementById("userid3").value;
  const taskId = document.getElementById("taskid3").value;
  const data = {
    userId: userId,
  };
  const options = {
    method: "PATCH",
    body: JSON.stringify(data),
    headers: {
      "Content-Type": "application/json",
    },
  };
  const url = `http://localhost:8808/task/assignee/${taskId}`;
  fetch(url, options)
    .then((response) => {
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      return response.json();
    })
    .then((data) => {
      console.log(data);
    })
    .catch((error) => {
      console.error("There was an error!", error);
    });
});

submitBtn4.addEventListener("click", (event) => {
  event.preventDefault();
  const taskId = document.getElementById("taskid4").value;
  const data = {
    completed: true,
  };
  const options = {
    method: "PATCH",
    body: JSON.stringify(data),
    headers: {
      "Content-Type": "application/json",
    },
  };
  const url = `http://localhost:8808/task/${taskId}`;
  fetch(url, options)
    .then((response) => {
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      return response.json();
    })
    .then((data) => {
      console.log(data);
    })
    .catch((error) => {
      console.error("There was an error!", error);
    });
});
