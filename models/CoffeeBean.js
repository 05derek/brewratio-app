const { DataTypes } = require('sequelize');
const sequelize = require('../config/database');
const User = require('./User');

const CoffeeBean = sequelize.define('CoffeeBean', {
  id: { type: DataTypes.INTEGER, autoIncrement: true, primaryKey: true },
  user_id: { type: DataTypes.INTEGER, allowNull: false },
  bean_name: { type: DataTypes.STRING, allowNull: false },
  origin: { type: DataTypes.STRING, allowNull: false },
  roast_level: { type: DataTypes.STRING, allowNull: false },
  process_type: { type: DataTypes.STRING, allowNull: false }
}, {
  tableName: 'coffee_beans',
  timestamps: false
});

User.hasMany(CoffeeBean, { foreignKey: 'user_id', onDelete: 'CASCADE' });
CoffeeBean.belongsTo(User, { foreignKey: 'user_id' });

module.exports = CoffeeBean;