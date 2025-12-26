<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Payment Gateway Simulation</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f4f6f8;
            padding: 30px;
        }
        .container {
            max-width: 500px;
            background: white;
            padding: 25px;
            margin: auto;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        input, button {
            width: 100%;
            padding: 10px;
            margin: 8px 0;
        }
        button {
            background: #0a66c2;
            color: white;
            border: none;
            cursor: pointer;
        }
        button:hover {
            background: #084a8f;
        }
        .result {
            margin-top: 15px;
            padding: 10px;
            background: #eef;
            border-radius: 5px;
            font-size: 14px;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Payment Gateway Simulation</h2>

    <input type="text" id="token" placeholder="JWT Token">

    <input type="text" id="cardNumber" placeholder="Card Number">
    <input type="text" id="expiry" placeholder="Expiry (MM/YY)">
    <input type="password" id="cvv" placeholder="CVV">
    <input type="number" id="amount" placeholder="Amount">

    <button onclick="makePayment()">Pay Now</button>

    <div class="result" id="result"></div>
</div>

<script>
    function makePayment() {
        const token = document.getElementById("token").value;

        const paymentData = {
            cardNumber: document.getElementById("cardNumber").value,
            expiry: document.getElementById("expiry").value,
            cvv: document.getElementById("cvv").value,
            amount: document.getElementById("amount").value
        };

        fetch("http://localhost:8080/paymentsx`", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + token
            },
            body: JSON.stringify(paymentData)
        })
        .then(response => response.json())
        .then(data => {
            document.getElementById("result").innerHTML =
                `<b>Status:</b> ${data.status}<br>
                 <b>Transaction ID:</b> ${data.transactionId}`;
        })
        .catch(error => {
            document.getElementById("result").innerHTML =
                "Payment Failed. Check API or Token.";
        });
    }
</script>

</body>
</html>

