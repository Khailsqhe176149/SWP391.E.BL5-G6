<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>X�c th?c m� OTP</title>
</head>
<body>
    <h2>X�c th?c m� OTP</h2>
    <form action="forgot-password" method="post">
        <label for="verificationCode">M� x�c th?c:</label>
        <input type="text" id="verificationCode" name="verificationCode" required>
        
        <label for="newPassword">M?t kh?u m?i:</label>
        <input type="password" id="newPassword" name="newPassword" required>
        
        <label for="confirmPassword">X�c nh?n m?t kh?u m?i:</label>
        <input type="password" id="confirmPassword" name="confirmPassword" required>

        <button type="submit" name="action" value="confirmVerificationCode">X�c nh?n v� thay ??i m?t kh?u</button>
    </form>
</body>
</html>
