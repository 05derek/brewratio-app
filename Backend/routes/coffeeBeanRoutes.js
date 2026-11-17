const router = require('express').Router();
const ctrl = require('../controllers/coffeeBeanController');
const verifyToken = require('../middleware/authMiddleware'); // SESUAIKAN NAMA FILE JWT ANDA

router.use(verifyToken); 
router.post('/', ctrl.create);
router.get('/', ctrl.getAll);
router.get('/search', ctrl.search);
router.put('/:id', ctrl.update);
router.delete('/:id', ctrl.delete);

module.exports = router;