// Chesstracking SPA
import {retrievePlayer} from "./player-comparison.js";
// Vars, functions, dom events


let playerArray = [];
let top100boardState = false;
const top100blitz = document.getElementById('top100-blitz');
const positionchangeview = document.getElementById('position-change-view');
const playercomparison = document.getElementById('player-comparison')
const views = ['#top100-blitz', '#player-comparison', '#position-change-view'];


// Functions

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


function addRankingNr() {
    playerArray.sort((a, b) => a.value - b.value);
}

function render(location) {
    if (views.includes(location)) {
        switch (location) {
            case "#top100-blitz":
                top100blitz.classList.remove('hidden');
                playercomparison.classList.add('hidden');
                positionchangeview.classList.add('hidden');
                break;
            case "#position-change-view":
                positionchangeview.classList.remove('hidden');
                top100blitz.classList.add('hidden');
                playercomparison.classList.add('hidden');
                break;
            case "#player-comparison":
                playercomparison.classList.remove('hidden');
                top100blitz.classList.add('hidden');
                positionchangeview.classList.add('hidden');
                break;
        }
    }
}

async function retrieveTop100() {
    const url = "/api/top100";
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
    let playerRank = 1; // Adding a player rank manually here. Hack
    const board = document.getElementById('top100-board');
    board.classList.remove('hidden');
    const tableBody = document.getElementById('table-body')
    tableBody.innerHTML = '';
    items.forEach(item => {
        const row = document.createElement('tr');
        row.innerHTML = `
        <td>${playerRank}</td>
        <td>${item.username}</td>
        <td>${item.rating}</td>
        `;
        tableBody.appendChild(row);
        playerRank++;
    })
}

// Event listeners
document.addEventListener('DOMContentLoaded', () => {
    render("#top100-blitz")
    window.addEventListener("hashchange", () => {
        render(location.hash)
    });
    retrieveTop100().then(showTop100);
})

console.log("im working lol");
const player = "cutemouse83"
console.log(retrievePlayer(player));
