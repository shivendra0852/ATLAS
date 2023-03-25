const submitAdminRegistrationForm = async (event) => {
    event.preventDefault(); // prevent form submission and page refresh
    const adminName = document.getElementById("admin_name").value;
    const adminMobile = document.getElementById("admin_mobile").value;
    const adminPassword = document.getElementById("admin_password").value;
    const adminConfirmPassword = document.getElementById("admin_confirm-password").value;
  
    if (!adminName || !adminMobile || !adminPassword || !adminConfirmPassword) {
        alert("Please fill in all the details");
        return;
    }


    if (adminPassword !== adminConfirmPassword) {
      alert("Passwords do not match");
      return;
    }
  
    const data = {
      name: adminName,
      mobileNo: adminMobile, // added mobileNo field
      password: adminPassword,
    };
  
    const options = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    };
  
    const response = await fetch("http://localhost:8888/registerAdmin", options);
    const responseData = await response.json();
  
    if (response.ok) {
      alert("Registration successful");
      window.location.href="loginPageAdmin.html";
    } else {
      alert("Registration failed");
    }
  };
  