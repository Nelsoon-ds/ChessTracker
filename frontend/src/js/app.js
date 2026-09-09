function showTop100() {
    if (top100boardState === false) {
        renderTable(playerArray)
        top100boardState = true;
    } else {
        const board = document.getElementById('top100-board');
        board.classList.add('hidden');
        top100boardState = false;

    }

}

let top100boardState = false;
const top100blitz = document.getElementById('top100-blitz');
const positionchangeview = document.getElementById('position-change-view');
const playercomparison = document.getElementById('player-comparison')
const views = ['#top100-blitz', '#player-comparison', '#position-change-view'];

function render() {

    window.addEventListener("hashchange", () => {
        let location = location.hash
        if (location in views) {
            switch (location) {
                case "#top100-blitz":
                    top100blitz.classList.remove('hidden');
                    playercomparison.classList.add('hidden');
                    positionchangeview.classList.add('hidden');
                case "#position-change-view":
                    positionchangeview.classList.remove('hidden');
                    top100blitz.classList.add('hidden');
                    playercomparison.classList.add('hidden');
                case "#player-comparison":
                    playercomparison.classList.remove('hidden');
                    top100blitz.classList.add('hidden');
                    positionchangeview.classList.add('hidden');
            }
        }
    })
}


let playerArray = [];

async function retrieveTop100() {
    const url = "/get/top100";
    try {
        const response = await fetch(url);
        if (!response.ok) {
            throw new Error(`Response status: ${response.status}`);
        }
        playerArray = await response.json();
    } catch (err) {
        console.log(err.message);
    }
}


function renderTable(items) {
    const board = document.getElementById('top100-board');
    board.classList.remove('hidden');
    const tableBody = document.getElementById('table-body')
    console.log(tableBody);
    tableBody.innerHTML = '';
    items.forEach(item => {
        const row = document.createElement('tr');
        console.log(item.rating);
        row.innerHTML = `
        <td>${item.username}</td>
        <td>${item.rating}</td>
        `;
        tableBody.appendChild(row);
    })
}

document.addEventListener('DOMContentLoaded', () => {
    retrieveTop100();
    showTop100();
})

showTop100();
render();
