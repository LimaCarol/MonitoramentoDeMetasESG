document.addEventListener('DOMContentLoaded', function() {
    fetch('metas.json')
        .then(response => response.json())
        .then(data => {
            const ctx = document.getElementById('line-chart-metas').getContext('2d'); // Alteração aqui
            const meses = data.consumoMensal.map(obj => obj.mes);
            const consumoDiesel = data.consumoMensal.map(obj => obj.consumoDiesel);

            new Chart(ctx, {
                type: 'line',
                data: {
                    labels: meses,
                    datasets: [{
                        label: 'Meta de diminuição de CO2 para o proximo ano',
                        data: consumoDiesel,
                        backgroundColor: '#CAF0F8',
                        borderColor: '#90E0EF',
                        borderWidth: 1,
                        fill: true
                    }]
                },
                options: {
                    scales: {
                        y: {
                            beginAtZero: true
                        }
                    }
                }
            });
        })
        .catch(error => console.error('Erro ao carregar o JSON:', error));
});

