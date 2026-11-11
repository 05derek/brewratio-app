const express = require('express');
const cors = require('cors');
require('dotenv').config();
const sequelize = require('./config/database');

// Import models untuk inisialisasi entitas database
require('./models/User');
require('./models/CoffeeBean');
require('./models/BrewMethod');
require('./models/BrewLog');

const app = express();
app.use(cors());
app.use(express.json());

// Cek koneksi & Sinkronisasi Database
sequelize.authenticate()
  .then(async () => {
    console.log('Database MySQL terhubung via XAMPP...');
    // Sinkronisasi otomatis model ke database
    await sequelize.sync({ alter: true });
    console.log('Semua entitas model berhasil disinkronkan ke database.');
  })
  .catch(err => console.log('Gagal koneksi database: ' + err));

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
  console.log(`Server BrewRatio berjalan di port ${PORT}`);
});