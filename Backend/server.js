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

// ================= TAMBAHAN HARI INI =================
const coffeeBeanRoutes = require('./routes/coffeeBeanRoutes');
const brewMethodRoutes = require('./routes/brewMethodRoutes');
const brewLogRoutes = require('./routes/brewLogRoutes');
// =====================================================

app.use('/api/auth', authRoutes);
app.use('/api/users', userRoutes);

// ================= TAMBAHAN HARI INI =================
app.use('/api/coffee-beans', coffeeBeanRoutes);
app.use('/api/brew-methods', brewMethodRoutes);
app.use('/api/brew-logs', brewLogRoutes);
// =====================================================

app.get('/', (req, res) => {
  res.json({ message: 'Server BrewRatio aktif!' });
});

// 3. Jalankan Server & Database
sequelize.authenticate()
  .then(async () => {
    console.log('Database MySQL terhubung via XAMPP...');
    
    // ================= TAMBAHAN HARI INI =================
    // Wajib dipanggil agar Sequelize membaca relasi (Foreign Key & Cascade Delete)
    // sebelum melakukan sync ke database MySQL
    require('./models/User');
    require('./models/CoffeeBean');
    require('./models/BrewMethod');
    require('./models/BrewLog');
    // =====================================================

    await sequelize.sync({ alter: true }); 
    console.log('Tabel dan Relasi berhasil disinkronisasi ke database.');
  })
  .catch(err => console.log('Gagal koneksi database: ' + err));

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
  console.log(`Server berjalan di port ${PORT}`);
});