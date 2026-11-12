const jwt = require('jsonwebtoken');
const JWT_SECRET = process.env.JWT_SECRET || 'brewratio_super_secret_key';

module.exports = (req, res, next) => {
  const authHeader = req.headers['authorization'];
  const token = authHeader && authHeader.split(' ')[1];

  if (!token) {
    return res.status(401).json({ 
      success: false, 
      message: 'Akses ditolak. Token tidak ditemukan.' 
    });
  }

  jwt.verify(token, JWT_SECRET, (err, user) => {
    if (err) {
      // Deteksi spesifik jika token sudah kedaluwarsa (expired)
      if (err.name === 'TokenExpiredError') {
        return res.status(401).json({ 
          success: false, 
          error_code: 'TOKEN_EXPIRED',
          message: 'Sesi Anda telah kedaluwarsa, silakan login kembali.' 
        });
      }
      
      // Jika token tidak valid / dimanipulasi
      return res.status(403).json({ 
        success: false, 
        message: 'Token tidak valid.' 
      });
    }
    
    req.user = user;
    next();
  });
};