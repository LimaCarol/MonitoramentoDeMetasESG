document.addEventListener('DOMContentLoaded', function() {
    fetch('emissoes.json')
        .then(response => response.json())
        .then(data => {
            const ctx = document.getElementById('barchart').getContext('2d');
            const meses = [];
            const emissaoCO2 = [];

            data.emissoesMensais.forEach(item => {
                meses.push(item.mes);
                emissaoCO2.push(item.emissaoCO2);
            });

            new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: meses,
                    datasets: [{
                        label: 'Total de CO2 Emitido em 2023',
                        data: emissaoCO2,
                        backgroundColor: '#748CAB',
                        borderColor: '#3E5C76',
                        borderWidth: 1
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
});
