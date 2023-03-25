const submitUserRegistrationForm = async (event) => {
    event.preventDefault();
    const userName = document.getElementById("user_name").value;
    const userMobile = document.getElementById("user_mobile").value;
    const userPassword = document.getElementById("user_password").value;
    const userConfirmPassword = document.getElementById("user_confirm-password").value;
  
    if (!userName || !userMobile || !userPassword || !userConfirmPassword) {
        alert("Please fill in all the details");
        return;
    }

    if (userPassword !== userConfirmPassword) {
      alert("Passwords do not match");
      return;
    }
  
    const data = {
      name: userName,
      mobileNo: userMobile,
      password: userPassword,
    };
  
    const options = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    };
  
    const response = await fetch("http://localhost:8808/registerUser", options);
    const responseData = await response.json();
  
    if (response.ok) {
      alert("Registration successful");
      window.location.href="loginPageUser.html";
    } else {
      alert("Registration failed");
    }
  };
