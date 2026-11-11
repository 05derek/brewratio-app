const { DataTypes } = require('sequelize');
const sequelize = require('../config/database');
const User = require('./User');
const CoffeeBean = require('./CoffeeBean');
const BrewMethod = require('./BrewMethod');

const BrewLog = sequelize.define('BrewLog', {
  id: { type: DataTypes.INTEGER, autoIncrement: true, primaryKey: true },
  user_id: { type: DataTypes.INTEGER, allowNull: false },
  bean_id: { type: DataTypes.INTEGER, allowNull: false },
  method_id: { type: DataTypes.INTEGER, allowNull: false },
  coffee_weight_grams: { type: DataTypes.FLOAT, allowNull: false },
  water_volume_ml: { type: DataTypes.FLOAT, allowNull: false },
  water_temp_celsius: { type: DataTypes.INTEGER, allowNull: false },
  rating: { type: DataTypes.INTEGER, allowNull: false },
  flavor_notes: { type: DataTypes.TEXT, allowNull: true }
}, {
  tableName: 'brew_logs',
  timestamps: true,
  createdAt: 'created_at',
  updatedAt: false
});

User.hasMany(BrewLog, { foreignKey: 'user_id', onDelete: 'CASCADE' });
BrewLog.belongsTo(User, { foreignKey: 'user_id' });

CoffeeBean.hasMany(BrewLog, { foreignKey: 'bean_id', onDelete: 'CASCADE' });
BrewLog.belongsTo(CoffeeBean, { foreignKey: 'bean_id' });

BrewMethod.hasMany(BrewLog, { foreignKey: 'method_id', onDelete: 'CASCADE' });
BrewLog.belongsTo(BrewMethod, { foreignKey: 'method_id' });

module.exports = BrewLog;