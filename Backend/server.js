const express = require('express');
const cors = require('cors');
require('dotenv').config();
const sequelize = require('./config/database');

const app = express();

// 1. WAJIB ADA AGAR BISA MEMBACA JSON DARI POSTMAN
app.use(cors());
app.use(express.json());

// 2. Import dan Daftarkan Routes setelah express.json()
const authRoutes = require('./routes/authRoutes');
const userRoutes = require('./routes/userRoutes');

app.use('/api/auth', authRoutes);
app.use('/api/users', userRoutes);

app.get('/', (req, res) => {
  res.json({ message: 'Server BrewRatio aktif!' });
});

// 3. Jalankan Server & Database
sequelize.authenticate()
  .then(async () => {
    console.log('Database MySQL terhubung via XAMPP...');
    await sequelize.sync({ alter: true });
  })
  .catch(err => console.log('Gagal koneksi database: ' + err));

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
  console.log(`Server berjalan di port ${PORT}`);
});