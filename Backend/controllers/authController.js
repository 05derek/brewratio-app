const User = require('../models/User');
const bcrypt = require('bcrypt');
const jwt = require('jsonwebtoken');

const JWT_SECRET = process.env.JWT_SECRET || 'brewratio_super_secret_key';

// Fungsi Register
const register = async (req, res) => {
  try {
    if (!req.body) {
      return res.status(400).json({ message: 'Request body tidak boleh kosong.' });
    }

    const { name, email, password, bio, favorite_roast, phone_number } = req.body;

    if (!name || !email || !password) {
      return res.status(400).json({ message: 'Nama, email, dan password wajib diisi!' });
    }

    const existingUser = await User.findOne({ where: { email } });
    if (existingUser) {
      return res.status(400).json({ message: 'Email sudah terdaftar!' });
    }

    const hashedPassword = await bcrypt.hash(password, 10);

    const newUser = await User.create({
      name,
      email,
      password: hashedPassword,
      bio,
      favorite_roast,
      phone_number
    });

    res.status(201).json({
      message: 'Registrasi berhasil!',
      data: {
        id: newUser.id,
        name: newUser.name,
        email: newUser.email
      }
    });
  } catch (error) {
    console.error(error);
    res.status(500).json({ message: 'Terjadi kesalahan pada server', error: error.message });
  }
};

// Fungsi Login
const login = async (req, res) => {
  try {
    const { email, password } = req.body;

    const user = await User.findOne({ where: { email } });
    if (!user) {
      return res.status(404).json({ message: 'Email tidak ditemukan.' });
    }

    const isPasswordValid = await bcrypt.compare(password, user.password);
    if (!isPasswordValid) {
      return res.status(401).json({ message: 'Password salah.' });
    }

    const token = jwt.sign({ id: user.id, email: user.email }, JWT_SECRET, { expiresIn: '1d' });

    res.status(200).json({
      message: 'Login berhasil!',
      token,
      data: {
        id: user.id,
        name: user.name,
        email: user.email
      }
    });
  } catch (error) {
    console.error(error);
    res.status(500).json({ message: 'Terjadi kesalahan pada server', error: error.message });
  }
};

// EXPORT SEMUA FUNGSI DENGAN BENAR
module.exports = {
  register,
  login
};