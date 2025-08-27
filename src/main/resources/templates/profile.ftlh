<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css" rel="stylesheet"/>
</head>
<body>
<div class="container">
    <div class="card" style="margin-top: 3rem;">
        <div class="card-content">
            <span class="card-title">Your Profile</span>
            <p><strong>Name:</strong> ${name}</p>
            <p><strong>Email:</strong> ${email}</p>
        </div>
        <div class="card-action">
            <a href="/signOut" class="btn red">Sign Out</a>
        </div>
    </div>
</div>
</body>
</html>
