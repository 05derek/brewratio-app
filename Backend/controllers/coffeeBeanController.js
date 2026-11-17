const CoffeeBean = require('../models/CoffeeBean');
const { Op } = require('sequelize');

exports.create = async (req, res) => {
  try {
    const data = await CoffeeBean.create({ ...req.body, user_id: req.user.id });
    res.status(201).json(data);
  } catch (err) { res.status(500).json({ error: err.message }); }
};

exports.getAll = async (req, res) => {
  try {
    const data = await CoffeeBean.findAll({ where: { user_id: req.user.id } });
    res.json(data);
  } catch (err) { res.status(500).json({ error: err.message }); }
};

exports.search = async (req, res) => {
  try {
    const { q } = req.query;
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
  } catch (err) { res.status(500).json({ error: err.message }); }
};

exports.update = async (req, res) => {
  try {
    const data = await CoffeeBean.findOne({ where: { id: req.params.id, user_id: req.user.id } });
    if (!data) return res.status(404).json({ message: "Data tidak ditemukan" });
    await data.update(req.body);
    res.json(data);
  } catch (err) { res.status(500).json({ error: err.message }); }
};

exports.delete = async (req, res) => {
  try {
    const data = await CoffeeBean.findOne({ where: { id: req.params.id, user_id: req.user.id } });
    if (!data) return res.status(404).json({ message: "Data tidak ditemukan" });
    await data.destroy();
    res.json({ message: "Berhasil dihapus" });
  } catch (err) { res.status(500).json({ error: err.message }); }
};