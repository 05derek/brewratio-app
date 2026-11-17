const BrewLog = require('../models/BrewLog');
const CoffeeBean = require('../models/CoffeeBean');
const BrewMethod = require('../models/BrewMethod');
const { Op } = require('sequelize');

exports.create = async (req, res) => {
  try {
    const data = await BrewLog.create({ ...req.body, user_id: req.user.id });
    res.status(201).json(data);
  } catch (err) { res.status(500).json({ error: err.message }); }
};

exports.getAll = async (req, res) => {
  try {
    const data = await BrewLog.findAll({ 
      where: { user_id: req.user.id },
      include: [CoffeeBean, BrewMethod] // Agar nama biji & metode ikut tampil
    });
    res.json(data);
  } catch (err) { res.status(500).json({ error: err.message }); }
};

exports.search = async (req, res) => {
  try {
    const { q } = req.query;
    const data = await BrewLog.findAll({
      where: { user_id: req.user.id },
      include: [{
        model: CoffeeBean,
        where: { bean_name: { [Op.like]: `%${q}%` } }
      }, BrewMethod]
    });
    res.json(data);
  } catch (err) { res.status(500).json({ error: err.message }); }
};

exports.update = async (req, res) => {
  try {
    const data = await BrewLog.findOne({ where: { id: req.params.id, user_id: req.user.id } });
    if (!data) return res.status(404).json({ message: "Data tidak ditemukan" });
    
    // REQ-20: Hanya update rating dan flavor_notes
    const { rating, flavor_notes } = req.body;
    await data.update({ rating, flavor_notes });
    
    res.json(data);
  } catch (err) { res.status(500).json({ error: err.message }); }
};

exports.delete = async (req, res) => {
  try {
    const data = await BrewLog.findOne({ where: { id: req.params.id, user_id: req.user.id } });
    if (!data) return res.status(404).json({ message: "Data tidak ditemukan" });
    await data.destroy();
    res.json({ message: "Berhasil dihapus" });
  } catch (err) { res.status(500).json({ error: err.message }); }
};