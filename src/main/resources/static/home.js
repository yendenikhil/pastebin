const showBins = new Vue({
    el: "#show-bins",
    data: {
        q: "",
        bins: []
    },
    methods: {
        prevPage: function() {
            console.log("prev page called");
        },
        nextPage: function() {
            console.log("next page called");
        },
        search: function() {
            console.log("search called");
        }
    },
    created: function() {
        getData("/api/bins")
        .then(resp => this.bins = resp );
    }

});
const createBin = new Vue({
    el: "#create-bin",
    data: {
        bin: {
            title: "",
            content: ""
        }
    },
    methods: {
        createBin: function () {
            console.log(JSON.stringify(this.bin));
            postData("/api/bins", JSON.stringify(this.bin))
            .then(resp => console.log(resp) );
        }
    }
});

function postData(url, payload={}) {
    return fetch(url,{
        method: 'POST',
        cache: 'no-cache',
        headers: {
            "Content-Type": "application/json"
        },
        redirect: "follow",
        referrer: "no-referrer",
        body: payload
    }).then(resp => resp.json() );
}

function getData(url) {
    return fetch(url,{
        method: 'GET',
        cache: 'no-cache',
        headers: {
            "Content-Type": "application/json"
        },
        redirect: "follow",
        referrer: "no-referrer"
    }).then(resp => resp.json() );
}