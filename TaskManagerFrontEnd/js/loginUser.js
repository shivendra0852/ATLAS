const loginUser = async (event) => {
    event.preventDefault(); // prevent form submission and page refresh
    const userMobile = document.getElementById("user_mobile").value;
    const userPassword = document.getElementById("user_password").value;
  
    const data = {
      mobileNo: userMobile,
      password: userPassword,
    };

    if (!userMobile || !userPassword) {
        alert("Please fill in all the details");
        return;
    }
  
    const options = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    };
  
    const response = await fetch("http://localhost:8808/loginUser", options);
    const responseData = await response.json();
    console.log(responseData);
  
    if (response.ok) {
      alert("Login successful");
      localStorage.setItem('userData', JSON.stringify(responseData));
      window.location.href="userLandingPage.html";
    } else {
      alert("Login failed");
      // display an error message to the user here
    }
  };
