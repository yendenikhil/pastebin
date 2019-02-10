document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.tooltipped');
    var instances = M.Tooltip.init(elems);
});

var search = new Vue({
    el: "#search",
    data: {
        message: "",
        bins: [],
        pageNum: 0,
        pageSize: 10
    },
    methods: {
        runQuery: function() {
            fetch("/api/list?q=" + this.message.trim()+"&pageNum="+this.pageNum+"&pageSize="+this.pageSize)
            .then(resp => resp.json())
            .then(resp => {
                if(resp.length > 0 || this.pageNum == 0){
                    this.bins = resp;
                    this.bins.filter(b => b.title.trim().length ==0).map(b => b.title = "Untitled");
                } else {
                this.pageNum--;
                console.log("Already on last page!");
                }
            })
        },
        findIt: function(e) {
            e.preventDefault();
            this.pageNum = 0;
            this.runQuery();
        },
        nextOne: function() {
            this.pageNum++;
            this.runQuery();
        },
        prevOne: function() {
            if(this.pageNum > 0) {
                this.pageNum--;
            this.runQuery();
            } else {
                console.log("Already on first page!");
            }
        }
    },
    created: function() {
                this.runQuery();
    }
});