const User = require('../models/User');

// 1. Ambil Profil User
exports.getProfile = async (req, res) => {
  try {
    const userId = req.user.id;
    const user = await User.findByPk(userId, {
      attributes: { exclude: ['password'] }
    });

    if (!user) {
      return res.status(404).json({ message: 'Pengguna tidak ditemukan.' });
    }

    res.status(200).json({
      message: 'Berhasil mengambil data profil',
      data: user
    });
  } catch (error) {
    console.error(error);
    res.status(500).json({ message: 'Terjadi kesalahan pada server', error: error.message });
  }
};

// 2. Update Profil User
exports.updateProfile = async (req, res) => {
  try {
    const userId = req.user.id;
    const { name, bio, favorite_roast, phone_number } = req.body;

    const user = await User.findByPk(userId);
    if (!user) {
      return res.status(404).json({ message: 'Pengguna tidak ditemukan.' });
    }

    user.name = name || user.name;
    user.bio = bio !== undefined ? bio : user.bio;
    user.favorite_roast = favorite_roast !== undefined ? favorite_roast : user.favorite_roast;
    user.phone_number = phone_number !== undefined ? phone_number : user.phone_number;

    await user.save();

    res.status(200).json({
      message: 'Profil berhasil diperbarui!',
      data: {
        id: user.id,
        name: user.name,
        email: user.email,
        bio: user.bio,
        favorite_roast: user.favorite_roast,
        phone_number: user.phone_number
      }
    });
  } catch (error) {
    console.error(error);
    res.status(500).json({ message: 'Terjadi kesalahan pada server', error: error.message });
  }
};