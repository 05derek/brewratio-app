const sequelize = require('./config/database');
const User = require('./models/User');
const CoffeeBean = require('./models/CoffeeBean');
const BrewMethod = require('./models/BrewMethod');
const BrewLog = require('./models/BrewLog');
const bcrypt = require('bcrypt');

const seedDatabase = async () => {
  try {
    await sequelize.authenticate();
    console.log('Koneksi database siap untuk seeding...');

    // 1. Buat User Dummy
    const hashedPassword = await bcrypt.hash('password123', 10);
    const user = await User.create({
      name: 'Sal Priadi',
      email: 'salpri@gmail.com',
      password: hashedPassword
    });
    console.log('User dibuat: salpri@gmail.com (pass: password123)');

    // 2. Buat Biji Kopi
    const bean1 = await CoffeeBean.create({
      user_id: user.id, bean_name: 'Gayo Arabica', origin: 'Aceh', roast_level: 'Medium', process_type: 'Washed'
    });
    const bean2 = await CoffeeBean.create({
      user_id: user.id, bean_name: 'Toraja Sapan', origin: 'Sulawesi', roast_level: 'Medium-Dark', process_type: 'Natural'
    });

    // 3. Buat Metode Seduh
    const method1 = await BrewMethod.create({
      user_id: user.id, method_name: 'V60', default_ratio: '1:15', grind_size: 'Medium-Fine', instructions: 'Tuang 30ml air untuk blooming, diamkan 30 detik...'
    });

    // 4. Buat Logbook Dummy (Menggunakan relasi Biji & Metode di atas)
    await BrewLog.create({
      user_id: user.id,
      coffee_bean_id: bean1.id,
      brew_method_id: method1.id,
      coffee_weight_grams: 15,
      water_volume_ml: 225,
      water_temp_celsius: 92,
      rating: 5,
      flavor_notes: 'Sangat fruity, ada hints of lemon dan honey.'
    });

    console.log('Data Biji Kopi, Metode, dan Logbook berhasil ditambahkan!');
    process.exit(0);
  } catch (error) {
    console.error('Gagal melakukan seeding:', error);
    process.exit(1);
  }
};

seedDatabase();