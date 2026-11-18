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

    // 1. Buat User Dummy (Cek berdasarkan email)
    const hashedPassword = await bcrypt.hash('password123', 10);
    const [user, userCreated] = await User.findOrCreate({
      where: { email: 'salpri@gmail.com' },
      defaults: {
        name: 'Sal Priadi',
        password: hashedPassword,
        bio: 'Home Brewer & Coffee Enthusiast',
        favorite_roast: 'Medium',
        phone_number: '08123456789'
      }
    });

    if (userCreated) {
      console.log('User baru dibuat: salpri@gmail.com');
    } else {
      console.log('User salpri@gmail.com sudah ada, lanjut ke data berikutnya...');
    }

    // 2. Buat Biji Kopi (Cek berdasarkan nama biji dan user_id)
    const [bean1] = await CoffeeBean.findOrCreate({
      where: { bean_name: 'Gayo Arabica', user_id: user.id },
      defaults: {
        origin: 'Aceh', 
        roast_level: 'Medium', 
        process_type: 'Washed'
      }
    });

    const [bean2] = await CoffeeBean.findOrCreate({
      where: { bean_name: 'Toraja Sapan', user_id: user.id },
      defaults: {
        origin: 'Sulawesi', 
        roast_level: 'Medium-Dark', 
        process_type: 'Natural'
      }
    });

    // 3. Buat Metode Seduh (Cek berdasarkan method_name)
    const [method1] = await BrewMethod.findOrCreate({
      where: { method_name: 'V60 4:6 Method' },
      defaults: {
        default_ratio: 15.00, 
        grind_size: 'Medium-Coarse', 
        instructions: 'Bagi tuangan air menjadi 40% (rasa manis/asam) dan 60% (kekuatan rasa). Tuang dalam 5 interval 45 detik.'
      }
    });

    const [method2] = await BrewMethod.findOrCreate({
      where: { method_name: 'V60 Single Pour' },
      defaults: {
        default_ratio: 16.00, 
        grind_size: 'Medium-Fine', 
        instructions: 'Tuang 50ml air untuk blooming selama 30 detik, lalu tuang sisa air secara perlahan dan konstan hingga selesai.'
      }
    });

    // 4. Buat Logbook Dummy (Cek berdasarkan kombinasi user, bean, dan method)
    const [log] = await BrewLog.findOrCreate({
      where: { 
        user_id: user.id,
        bean_id: bean1.id,
        method_id: method1.id
      },
      defaults: {
        coffee_weight_grams: 15.0,
        water_volume_ml: 225.0,
        water_temp_celsius: 92,
        rating: 5,
        flavor_notes: 'Sangat fruity, ada hints of lemon, peach, dan honey finish.'
      }
    });

    console.log('Data Biji Kopi, Metode, dan Logbook berhasil diverifikasi/ditambahkan!');
    process.exit(0);
  } catch (error) {
    console.error('Gagal melakukan seeding:', error);
    process.exit(1);
  }
};

seedDatabase();