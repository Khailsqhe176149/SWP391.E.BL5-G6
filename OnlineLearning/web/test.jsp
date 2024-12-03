<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Custom Checkbox</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/css/bootstrap.min.css" rel="stylesheet" />
    <style>
        /* Tùy ch?nh checkbox */
        .custom-checkbox .form-check-input {
            width: 30px;
            height: 30px;
            border-radius: 50%; /* T?o hình tròn */
            border: 2px solid #007bff; /* Màu vi?n */
            background-color: white; /* Màu n?n m?c ??nh */
            position: relative;
        }

        /* Màu n?n khi ???c ch?n */
        .custom-checkbox .form-check-input:checked {
            background-color: #28a745; /* Màu n?n khi ch?n */
            border-color: #28a745; /* Màu vi?n khi ch?n */
        }

        /* D?u checkmark */
        .custom-checkbox .form-check-input:checked::before {
            content: '\2713'; /* D?u tick */
            font-size: 20px;
            position: absolute;
            top: 3px;
            left: 6px;
            color: white; /* Màu c?a d?u tích */
        }

        .form-check-label {
            margin-left: 10px;
            font-size: 16px;
        }

        .payment-options {
            margin-top: 20px;
        }

        .payment-icon {
            width: 30px;
            height: 30px;
            object-fit: cover;
        }

        .card-body {
            padding: 20px;
        }

        .course-info-card .wallet-info {
            margin-top: 20px;
        }

        .course-price {
            font-size: 20px;
            font-weight: bold;
            color: #28a745;
        }
    </style>
</head>
<body>
 <div class="container mt-5">
    <div class="row">
        <!-- C?t trái: L?a ch?n thanh toán (Không n?m trong card) -->
        <div class="col-md-6">
            <h4>Payment Options</h4>
            <!-- Các l?a ch?n thanh toán -->
            <div class="payment-options">
                <div class="row">
                    <!-- C?t 1: Pay by Wallet -->
                    <div class="col-md-6">
                        <div class="form-check custom-checkbox">
                            <input class="form-check-input" type="checkbox" id="payByWallet" checked disabled>
                            <img src="img/Wallet1.jpg" alt="Wallet" class="payment-icon mr-2">
                            <label class="form-check-label" for="payByWallet">
                                Pay by Wallet
                            </label>
                        </div>
                    </div>
                    <!-- C?t 2: Pay by Credit Card -->
                    <div class="col-md-6">
                        <div class="form-check custom-checkbox">
                            <input class="form-check-input" type="checkbox" id="payByCredit" disabled>
                            <img src="img/CreditCard.jpg" alt="Credit Card" class="payment-icon mr-2">
                            <label class="form-check-label" for="payByCredit">
                                Pay by Credit Card
                            </label>
                        </div>
                    </div>
                </div>
                <div class="row mt-3">
                    <!-- C?t 3: Pay by PayPal -->
                    <div class="col-md-6">
                        <div class="form-check custom-checkbox">
                            <input class="form-check-input" type="checkbox" id="payByPaypal" disabled>
                            <img src="img/vietQR.jpg" alt="PayPal" class="payment-icon mr-2">
                            <label class="form-check-label" for="payByPaypal">
                                Pay by VietQR
                            </label>
                        </div>
                    </div>
                    <!-- C?t 4: Pay by Bank Transfer -->
                    <div class="col-md-6">
                        <div class="form-check custom-checkbox">
                            <input class="form-check-input" type="checkbox" id="payByBank" disabled>
                            <img src="img/VnPay.jpg" alt="Bank Transfer" class="payment-icon mr-2">
                            <label class="form-check-label" for="payByBank">
                                Pay by VNPay
                            </label>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- C?t ph?i: Thông tin khóa h?c và ví ng??i dùng (Trong th? card) -->
        <div class="col-md-6">
            <div class="card course-info-card">
                <div class="card-body">
                    <h2 class="card-title">Learn Web Development</h2>
                    <p><strong>Instructor:</strong> John Doe</p>
                    <p><strong>Category:</strong> Web Development</p>
                    <p class="course-price">Price: $99.99</p>

                    <!-- Thông tin ví c?a ng??i dùng -->
                    <div class="wallet-info">
                        <p><strong>Your Wallet:</strong> $150.00</p>
                        <p><strong>Your Wallet After Pay:</strong> $50.00</p>
                    </div>

                    <!-- Nút ??ng Ký khóa h?c -->
                    <form action="RegisterCourseServlet" method="POST">
                        <!-- Thêm hidden input ?? g?i courseId -->
                        <input type="hidden" name="courseId" value="1" />
                        <button type="submit" class="btn btn-primary mt-3">Enroll Now</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
