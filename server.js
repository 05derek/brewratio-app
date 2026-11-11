const express = require('express');
const cors = require('cors');
require('dotenv').config();
const sequelize = require('./config/database');

const app = express();
app.use(cors());
app.use(express.json());

// Cek koneksi Database
sequelize.authenticate()
  .then(() => console.log('Database MySQL terhubung via XAMPP...'))
  .catch(err => console.log('Gagal koneksi database: ' + err));

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
  console.log(`Server BrewRatio berjalan di port ${PORT}`);
});