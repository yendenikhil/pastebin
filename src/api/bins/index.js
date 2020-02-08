const router = require('express').Router();
const Bin = require('./bin.js');
const bins = [];
bins.push(new Bin(0, 'test title 1', 'test content 1'));
bins.push(new Bin(0, 'test title 2', 'test content 2'));

router.get('/', (req, res) => {
  let q = req.query.q;
  if(q) {
    res.json(bins.filter(b => b.title.includes(q)));
  } else {
    res.json(bins);
  }
});

router.post('/', (req, res) => {
  let b = new Bin(bins.length, req.body.title, req.body.content);
  bins.push(b);
  res.json({message: 'bin created'});
});

router.get('/:id', (req, res) =>{
  let id = req.params.id;
  let bin = bins.find(b => b.id == id);
  if(!bin) {
    res.json({error: 'bin with this id does not exists'});
  } else {
    res.json(bin);
  }
});

router.delete('/:id', (req, res) => {
  let id = req.params.id;
  let index = bins.findIndex(b => b.id == id);
  if(index < 0) {
    res.json({error: 'bin with this id does not exists.'});
  } else {
    bins.splice(index, 1);
    res.json({message: 'bin deleted'});
  } 
});

module.exports = router;
