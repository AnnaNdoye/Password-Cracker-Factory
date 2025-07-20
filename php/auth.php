<?php
// auth.php - Version corrigée
$correctLogin = "admin";
$correctPassword = "pass"; // Mot de passe cohérent avec le dictionnaire

$login = null;
$password = null;

// Gérer les requêtes JSON (pour le cracker Java)
$contentType = $_SERVER['CONTENT_TYPE'] ?? '';
if ($_SERVER['REQUEST_METHOD'] === 'POST' && strpos($contentType, 'application/json') !== false) {
    $json = file_get_contents('php://input');
    $data = json_decode($json, true);
    
    if ($data && isset($data['login']) && isset($data['password'])) {
        $login = $data['login'];
        $password = $data['password'];
        
        $success = $login === $correctLogin && $password === $correctPassword;
        
        header('Content-Type: application/json');
        echo json_encode([
            'status' => $success ? 'success' : 'error',
            'message' => $success ? 'Connexion réussie' : 'Échec de la connexion'
        ]);
        exit;
    }
}

// Gérer les requêtes de formulaire HTML (pour l'interface web)
if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_POST['login']) && isset($_POST['password'])) {
    $login = $_POST['login'];
    $password = $_POST['password'];
    
    $success = $login === $correctLogin && $password === $correctPassword;
    
    if ($success) {
        header('Location: bienvenue.php');
        exit;
    } else {
        $message = "Échec de la connexion";
    }
}
?>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Page d'Authentification</title>
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
        
        .login-container {
            background: rgba(255, 255, 255, 0.95);
            padding: 4rem 3rem;
            border-radius: 20px;
            box-shadow: 0 15px 35px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 500px;
            backdrop-filter: blur(10px);
            border: 1px solid rgba(255, 255, 255, 0.2);
        }
        
        h2 {
            text-align: center;
            margin-bottom: 2.5rem;
            color: #1976d2;
            font-size: 2.2rem;
            font-weight: 300;
            letter-spacing: 1px;
        }
        
        .form-group {
            margin-bottom: 2rem;
            position: relative;
        }
        
        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 1.2rem 1.5rem;
            border: 2px solid #e3f2fd;
            border-radius: 12px;
            font-size: 1.1rem;
            transition: all 0.3s ease;
            background: #fafafa;
            color: #333;
        }
        
        input[type="text"]:focus, input[type="password"]:focus {
            outline: none;
            border-color: #2196f3;
            background: #fff;
            box-shadow: 0 0 0 3px rgba(33, 150, 243, 0.1);
            transform: translateY(-2px);
        }
        
        input::placeholder {
            color: #999;
            font-weight: 300;
        }
        
        button {
            width: 100%;
            padding: 1.3rem;
            background: linear-gradient(135deg, #42a5f5 0%, #1976d2 100%);
            color: white;
            border: none;
            border-radius: 12px;
            cursor: pointer;
            font-size: 1.2rem;
            font-weight: 500;
            letter-spacing: 0.5px;
            transition: all 0.3s ease;
            margin-top: 1rem;
        }
        
        button:hover {
            transform: translateY(-3px);
            box-shadow: 0 10px 25px rgba(33, 150, 243, 0.3);
            background: linear-gradient(135deg, #1976d2 0%, #1565c0 100%);
        }
        
        button:active {
            transform: translateY(-1px);
        }
        
        .message {
            text-align: center;
            margin-top: 2rem;
            padding: 1rem;
            border-radius: 8px;
            font-weight: 500;
        }
        
        .message.error {
            background: rgba(244, 67, 54, 0.1);
            color: #d32f2f;
            border: 1px solid rgba(244, 67, 54, 0.2);
        }
        
        .form-title {
            text-align: center;
            margin-bottom: 1rem;
            color: #666;
            font-size: 1rem;
            font-weight: 300;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>Connexion</h2>
        <p class="form-title">Veuillez vous connecter à votre compte</p>
        <form method="POST">
            <div class="form-group">
                <input type="text" name="login" placeholder="Nom d'utilisateur" required>
            </div>
            <div class="form-group">
                <input type="password" name="password" placeholder="Mot de passe" required>
            </div>
            <button type="submit">Se connecter</button>
        </form>
        <?php if (isset($message)): ?>
            <div class="message error"><?= htmlspecialchars($message) ?></div>
        <?php endif; ?>
    </div>
</body>
</html>