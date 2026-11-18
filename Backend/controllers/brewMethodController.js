const BrewMethod = require('../models/BrewMethod');
const { Op } = require('sequelize');

// 1. Tambah Metode Seduh (Create - Sesuai SRS REQ-12)
exports.create = async (req, res) => {
  try {
    const data = await BrewMethod.create(req.body);
    res.status(201).json(data);
  } catch (err) { 
    res.status(500).json({ error: err.message }); 
  }
};

// 2. Ambil Semua Metode Seduh (Read - Sesuai SRS REQ-13)
exports.getAll = async (req, res) => {
  try {
    const data = await BrewMethod.findAll();
    res.json(data);
  } catch (err) { 
    res.status(500).json({ error: err.message }); 
  }
};

// 3. Cari Metode Seduh Berdasarkan Nama (Search - Sesuai SRS REQ-16)
exports.search = async (req, res) => {
  try {
    const q = req.query.q || '';
    const data = await BrewMethod.findAll({
      where: { 
        method_name: { [Op.like]: `%${q}%` } 
      }
    });
    res.json(data);
  } catch (err) { 
    res.status(500).json({ error: err.message }); 
  }
};

// 4. Update Metode Seduh (Update - Sesuai SRS REQ-14)
exports.update = async (req, res) => {
  try {
    const data = await BrewMethod.findByPk(req.params.id);
    if (!data) return res.status(404).json({ message: "Data tidak ditemukan" });
    await data.update(req.body);
    res.json(data);
  } catch (err) { 
    res.status(500).json({ error: err.message }); 
  }
};

// 5. Hapus Metode Seduh (Delete - Sesuai SRS REQ-15)
exports.delete = async (req, res) => {
  try {
    const data = await BrewMethod.findByPk(req.params.id);
    if (!data) return res.status(404).json({ message: "Data tidak ditemukan" });
    await data.destroy();
    res.json({ message: "Berhasil dihapus" });
  } catch (err) { 
    res.status(500).json({ error: err.message }); 
  }
};
