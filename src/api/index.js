const router = require('express').Router();

router.use('/bins', require('./bins'));
router.get('/', (req, res) => {
  res.send('in the api');
});

module.exports = router;
