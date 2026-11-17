const router = require('express').Router();
const ctrl = require('../controllers/brewLogController');
const verifyToken = require('../middleware/authMiddleware');

router.use(verifyToken);
router.post('/', ctrl.create);
router.get('/', ctrl.getAll);
router.get('/search', ctrl.search);
router.put('/:id', ctrl.update);
router.delete('/:id', ctrl.delete);

module.exports = router;