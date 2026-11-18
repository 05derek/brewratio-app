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
      password: hashedPassword,
      bio: 'Home Brewer & Coffee Enthusiast',
      favorite_roast: 'Medium',
      phone_number: '08123456789'
    });
    console.log('User dibuat: salpri@gmail.com (pass: password123)');

    // 2. Buat Biji Kopi
    const bean1 = await CoffeeBean.create({
      user_id: user.id, 
      bean_name: 'Gayo Arabica', 
      origin: 'Aceh', 
      roast_level: 'Medium', 
      process_type: 'Washed'
    });
    const bean2 = await CoffeeBean.create({
      user_id: user.id, 
      bean_name: 'Toraja Sapan', 
      origin: 'Sulawesi', 
      roast_level: 'Medium-Dark', 
      process_type: 'Natural'
    });

    // 3. Buat Metode Seduh (Master Data V60)
    const method1 = await BrewMethod.create({
      method_name: 'V60 4:6 Method', 
      default_ratio: 15.00, 
      grind_size: 'Medium-Coarse', 
      instructions: 'Bagi tuangan air menjadi 40% (rasa manis/asam) dan 60% (kekuatan rasa). Tuang dalam 5 interval 45 detik.'
    });
    const method2 = await BrewMethod.create({
      method_name: 'V60 Single Pour', 
      default_ratio: 16.00, 
      grind_size: 'Medium-Fine', 
      instructions: 'Tuang 50ml air untuk blooming selama 30 detik, lalu tuang sisa air secara perlahan dan konstan hingga selesai.'
    });

    // 4. Buat Logbook Dummy (Menggunakan relasi FK: bean_id & method_id)
    await BrewLog.create({
      user_id: user.id,
      bean_id: bean1.id,
      method_id: method1.id,
      coffee_weight_grams: 15.0,
      water_volume_ml: 225.0,
      water_temp_celsius: 92,
      rating: 5,
      flavor_notes: 'Sangat fruity, ada hints of lemon, peach, dan honey finish.'
    });

    console.log('Data Biji Kopi, Metode, dan Logbook berhasil ditambahkan!');
    process.exit(0);
  } catch (error) {
    console.error('Gagal melakukan seeding:', error);
    process.exit(1);
  }
};

seedDatabase();
