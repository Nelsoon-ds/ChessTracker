export async function retrievePlayer(userName) {
    const url = `api/players/${userName}`
    try {
        const response = await fetch(url);
        if (!response.ok) {
            throw new Error(`Response status: ${response.status}`);
        }
        showPlayer(await response.json());

    } catch (err) {
        console.log(err.message);
    }
}


function showPlayer(response) {
    const table = document.getElementById("player-1-table-body")
    table.innerHTML = '';
    const namespace = document.getElementById('player-1-name');
    namespace.innerHTML = '';

    const username = document.createElement("h2");
    username.innerHTML = response[0].username;

    namespace.appendChild(username);


    response.forEach(response => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${response.rating}</td>
            <td>${new Date(response.timestamp).toLocaleDateString("da-DK")}</td>
            `;
        table.appendChild(row);
    })

}


