var binEntry = new Vue({
    el: "#bin-entry",
    data: {
        bin: {
            title: "",
            content: ""
        }
    },
    methods: {
        saveIt: function(e) {
            e.preventDefault();
            console.log(JSON.stringify(this.bin));
            fetch("/api/list", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(this.bin)
            }).then(resp => resp.json())
            .then(resp => console.log(resp) );
        }
    }

});