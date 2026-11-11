const { DataTypes } = require('sequelize');
const sequelize = require('../config/database');

const BrewMethod = sequelize.define('BrewMethod', {
  id: { type: DataTypes.INTEGER, autoIncrement: true, primaryKey: true },
  method_name: { type: DataTypes.STRING, allowNull: false },
  default_ratio: { type: DataTypes.DECIMAL(5, 2), allowNull: false },
  grind_size: { type: DataTypes.STRING, allowNull: false },
  instructions: { type: DataTypes.TEXT, allowNull: false }
}, {
  tableName: 'brew_methods',
  timestamps: false
});

module.exports = BrewMethod;