var search = new Vue({
    el: "#search",
    data: {
        message: "",
        data: []
    },
    methods: {
        findIt: function(e) {
            e.preventDefault();
            fetch("/api/list?q=" + this.message)
            .then(resp => resp.json())
            .then(resp => this.data = resp)
        }
    }
});