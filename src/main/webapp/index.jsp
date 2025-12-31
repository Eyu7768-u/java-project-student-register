<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Student Portal</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #f4f7f6; margin: 0; display: flex; align-items: center; justify-content: center; height: 100vh; }
        .card { background: white; padding: 40px; border-radius: 12px; shadow: 0 4px 15px rgba(0,0,0,0.1); text-align: center; max-width: 400px; width: 90%; border: 1px solid #e1e4e8; }
        h1 { color: #333; margin-bottom: 20px; }
        .btn { display: block; padding: 12px; margin: 10px 0; border-radius: 6px; text-decoration: none; font-weight: bold; transition: 0.3s; }
        .btn-primary { background-color: #007bff; color: white; }
        .btn-primary:hover { background-color: #0056b3; }
        .btn-secondary { background-color: white; color: #555; border: 1px solid #ddd; }
        .btn-secondary:hover { border-color: #007bff; color: #007bff; }
    </style>
</head>
<body>
    <div class="card">
        <h1>Student Portal</h1>
        <p style="color: #666; margin-bottom: 30px;">Manage registrations and view student records efficiently.</p>
        <a href="StudentServlet" class="btn btn-primary">Register New Student</a>
        <a href="StudentServlet?view=list" class="btn btn-secondary">View Registered Students</a>
    </div>
</body>
</html>