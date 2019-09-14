const showBins = new Vue({
    el: "#show-bins",
    data: {
        q: "",
        bins: [],
        pageNum: 0
    },
    methods: {
        prevPage: function () {
            if (this.pageNum > 0) {
                this.pageNum -= 1;
                this.getBins();
            }
        },
        nextPage: function () {
            this.pageNum += 1;
            this.getBins();
        },
        search: function () {
            // reset page number for new query
            this.pageNum = 0;
            this.getBins();
        },
        getBins: function () {
            getData("/api/bins?pageNum=" + this.pageNum + "&q=" + this.q)
                .then(resp => {
                    // check whether empty results is due to missing matches.
                    if (this.pageNum > 0) {
                        // only update if there is something to update.
                        if (resp.length > 0) {
                            this.bins = resp;
                        } else {

                            // this means we reached to empty results and page number should be less by one.
                            this.pageNum -= 1;
                        }
                    } else {
                        this.bins = resp;
                    }

                });
        }
    },
    created: function () {
        this.getBins();
    }

});
const createBin = new Vue({
    el: "#create-bin",
    data: {
        bin: {
            title: "",
            content: ""
        },
        postMessage: ""
    },
    methods: {
        createBin: function () {
            console.log(JSON.stringify(this.bin));
            postData("/api/bins", JSON.stringify(this.bin))
                .then(resp => {
                    console.log(resp);
                    this.postMessage = JSON.stringify(resp);
                    showBins.getBins();
                });
        },
        clearBin: function() {
            this.bin.title = "";
            this.bin.content = "";
            this.postMessage = "";
        }
    }
});

function postData(url, payload = {}) {
    return fetch(url, {
        method: 'POST',
        cache: 'no-cache',
        headers: {
            "Content-Type": "application/json"
        },
        redirect: "follow",
        referrer: "no-referrer",
        body: payload
    }).then(resp => resp.json());
}

function getData(url) {
    return fetch(url, {
        method: 'GET',
        cache: 'no-cache',
        headers: {
            "Content-Type": "application/json"
        },
        redirect: "follow",
        referrer: "no-referrer"
    }).then(resp => resp.json());
}