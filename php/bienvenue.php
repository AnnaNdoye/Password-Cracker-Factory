<?php
// bienvenue.php
?>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bienvenue</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #e3f2fd 0%, #bbdefb 100%);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }
        
        .welcome-container {
            text-align: center;
            background: rgba(255, 255, 255, 0.95);
            padding: 4rem 3rem;
            border-radius: 20px;
            box-shadow: 0 15px 35px rgba(0, 0, 0, 0.1);
            backdrop-filter: blur(10px);
            border: 1px solid rgba(255, 255, 255, 0.2);
            max-width: 600px;
            width: 100%;
        }
        
        .welcome-message {
            color: #1976d2;
            font-size: 3rem;
            font-weight: 300;
            letter-spacing: 2px;
            margin-bottom: 1rem;
            animation: fadeInUp 1s ease-out;
        }
        
        .welcome-subtitle {
            color: #666;
            font-size: 1.2rem;
            font-weight: 300;
            animation: fadeInUp 1s ease-out 0.3s both;
        }
        
        @keyframes fadeInUp {
            from {
                opacity: 0;
                transform: translateY(30px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }
        
        .logout-link {
            display: inline-block;
            margin-top: 2rem;
            padding: 1rem 2rem;
            background: linear-gradient(135deg, #42a5f5 0%, #1976d2 100%);
            color: white;
            text-decoration: none;
            border-radius: 12px;
            font-weight: 500;
            transition: all 0.3s ease;
            animation: fadeInUp 1s ease-out 0.6s both;
        }
        
        .logout-link:hover {
            transform: translateY(-3px);
            box-shadow: 0 10px 25px rgba(33, 150, 243, 0.3);
        }
    </style>
</head>
<body>
    <div class="welcome-container">
        <h1 class="welcome-message">Bienvenu</h1>
        <p class="welcome-subtitle">Vous êtes maintenant connecté avec succès</p>
        <a href="auth.php" class="logout-link">Retour à la connexion</a>
    </div>
</body>
</html>