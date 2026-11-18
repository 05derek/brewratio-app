const CoffeeBean = require('../models/CoffeeBean');
const { Op } = require('sequelize');

// 1. Tambah Biji Kopi (Create - Sesuai SRS REQ-7)
exports.create = async (req, res) => {
  try {
    const data = await CoffeeBean.create({ ...req.body, user_id: req.user.id });
    res.status(201).json(data);
  } catch (err) { 
    res.status(500).json({ error: err.message }); 
  }
};

// 2. Ambil Semua Biji Kopi Milik User (Read - Sesuai SRS REQ-8)
exports.getAll = async (req, res) => {
  try {
    const data = await CoffeeBean.findAll({ where: { user_id: req.user.id } });
    res.json(data);
  } catch (err) { 
    res.status(500).json({ error: err.message }); 
  }
};

// 3. Cari Biji Kopi Berdasarkan Nama atau Origin (Search - Sesuai SRS REQ-11)
exports.search = async (req, res) => {
  try {
    const q = req.query.q || '';
    const data = await CoffeeBean.findAll({
      where: {
        user_id: req.user.id,
        [Op.or]: [
          { bean_name: { [Op.like]: `%${q}%` } },
          { origin: { [Op.like]: `%${q}%` } }
        ]
      }
    });
    res.json(data);
  } catch (err) { 
    res.status(500).json({ error: err.message }); 
  }
};

// 4. Update Data Biji Kopi (Update - Sesuai SRS REQ-9)
exports.update = async (req, res) => {
  try {
    const data = await CoffeeBean.findOne({ where: { id: req.params.id, user_id: req.user.id } });
    if (!data) return res.status(404).json({ message: "Data tidak ditemukan" });
    await data.update(req.body);
    res.json(data);
  } catch (err) { 
    res.status(500).json({ error: err.message }); 
  }
};

// 5. Hapus Biji Kopi (Delete - Sesuai SRS REQ-10)
exports.delete = async (req, res) => {
  try {
    const data = await CoffeeBean.findOne({ where: { id: req.params.id, user_id: req.user.id } });
    if (!data) return res.status(404).json({ message: "Data tidak ditemukan" });
    await data.destroy();
    res.json({ message: "Berhasil dihapus" });
  } catch (err) { 
    res.status(500).json({ error: err.message }); 
  }
};
