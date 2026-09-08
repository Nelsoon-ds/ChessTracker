document.addEventListener("DOMContentLoaded", () => {
    const viewtop100 = document.querySelector("#btn-view-top100");
    if (viewtop100) {
        viewtop100.addEventListener("click", async function (e) {
            console.log(this.className);
            e.preventDefault();
            const url = "/get/top100";
            try {
                const response = await fetch(url);
                if (!response.ok) {
                    throw new Error(`Response status: ${response.status}`);
                }
                const result = await response.json();
                console.log(result);
            } catch (err) {
                console.log(err.message);
            }
        });
    }
});

