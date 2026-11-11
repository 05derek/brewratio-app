const jwt = require('jsonwebtoken');
const JWT_SECRET = process.env.JWT_SECRET || 'brewratio_super_secret_key';

module.exports = (req, res, next) => {
  const authHeader = req.headers['authorization'];
  const token = authHeader && authHeader.split(' ')[1];

  if (!token) {
    return res.status(401).json({ message: 'Akses ditolak. Token tidak ditemukan.' });
  }

  jwt.verify(token, JWT_SECRET, (err, user) => {
    if (err) {
      return res.status(403).json({ message: 'Token tidak valid atau sudah kadaluarsa.' });
    }
    
    req.user = user;
    next();
  });
};