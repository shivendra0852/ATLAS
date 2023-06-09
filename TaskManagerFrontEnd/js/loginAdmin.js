const loginAdmin = async (event) => {
    event.preventDefault(); // prevent form submission and page refresh
    const adminMobile = document.getElementById("admin_mobile").value;
    const adminPassword = document.getElementById("admin_password").value;
  
    const data = {
      mobileNo: adminMobile,
      password: adminPassword,
    };

    if (!adminMobile || !adminPassword) {
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
  
    const response = await fetch("http://localhost:8808/loginAdmin", options);
    const responseData = await response.json();
    console.log(responseData);
  
    if (response.ok) {
      alert("Login successful");
      localStorage.setItem('adminData', JSON.stringify(responseData));
      window.location.href="adminLandingPage.html";
    } else {
      alert("Login failed");
      // display an error message to the user here
    }
  };
  