const express = require('express');
const router = express.Router();
const authController = require('../controllers/authController');

// Endpoint: POST /api/auth/register
router.post('/register', authController.register);

// Endpoint: POST /api/auth/login
router.post('/login', authController.login);

// Endpoint: POST /api/auth/refresh (Pedoman Kategori 1)
router.post('/refresh', authController.refreshToken);

module.exports = router;
