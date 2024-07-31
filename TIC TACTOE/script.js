document.addEventListener('DOMContentLoaded', () => {
    const board = document.getElementById('board');
    const cells = document.querySelectorAll('.cell');
    const message = document.getElementById('message');
    const restartButton = document.getElementById('restart-button');

    let currentPlayer = 'X';
    let gameActive = true;
    let gameState = ['', '', '', '', '', '', '', '', ''];

    const winningConditions = [
        [0, 1, 2], [3, 4, 5], [6, 7, 8], // rows
        [0, 3, 6], [1, 4, 7], [2, 5, 8], // columns
        [0, 4, 8], [2, 4, 6] // diagonals
    ];

    const checkWin = () => {
        for (let condition of winningConditions) {
            const [a, b, c] = condition;
            if (gameState[a] && gameState[a] === gameState[b] && gameState[a] === gameState[c]) {
                return gameState[a];
            }
        }
        return null;
    };

    const checkDraw = () => {
        return !gameState.includes('');
    };

    const handleCellClick = (e) => {
        const cellIndex = e.target.getAttribute('data-cell');

        if (gameState[cellIndex] !== '' || !gameActive) return;

        gameState[cellIndex] = currentPlayer;
        e.target.textContent = currentPlayer;

        const winner = checkWin();
        const draw = checkDraw();

        if (winner) {
            gameActive = false;
            announceWinner(winner);
        } else if (draw) {
            gameActive = false;
            message.textContent = 'It\'s a draw!';
        } else {
            currentPlayer = currentPlayer === 'X' ? 'O' : 'X';
            message.textContent = `Player ${currentPlayer}'s turn`;
        }
    };

    const announceWinner = (winner) => {
        message.innerHTML = `<span class="congrats">Congratulations!</span> Player ${winner} wins!`;
        message.classList.add('winner-message');
    };

    const handleRestart = () => {
        currentPlayer = 'X';
        gameActive = true;
        gameState = ['', '', '', '', '', '', '', '', ''];
        message.textContent = `Player ${currentPlayer}'s turn`;
        cells.forEach(cell => {
            cell.textContent = '';
            cell.classList.remove('win');
        });
        message.classList.remove('winner-message');
    };

    cells.forEach(cell => {
        cell.addEventListener('click', handleCellClick);
    });

    restartButton.addEventListener('click', handleRestart);
});
