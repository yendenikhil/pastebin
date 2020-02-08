function fetchBins(q='') {
  return fetch('/api/bins?q=' + q)
    .then(r => r.json());
}
const container = new Vue({
  el:'.container',
  data: {
    bins: [],
    q: ""
  },
  created: function() {
    fetchBins(this.q)
      .then(r => this.bins = r);
  },
  methods: {
    checkIn: function(e) {
      fetchBins(this.q)
        .then(r => this.bins = r);
    }
  }
});
