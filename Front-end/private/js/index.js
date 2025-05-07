
const estiloDoTextoDoGrafico = {
    color: '#000000',
    font: {
        weight: '500',
        family: "'Anybody', sans-serif",
        size: 12,
        lineHeight: 1.5  
    }
};


new Chart(document.getElementById('myChart'), {
    type: 'bar',
    data: {
        labels: ['Argentina', 'Chile', 'EUA', 'Paraguai', 'Uruguai'],
        datasets: [{
            data: [20, 15, 10, 5, 1],
            backgroundColor: '#C49F1B'
        }]
    },
    options: {
        responsive: true,
        maintainAspectRatio: false,
        plugins: {
            legend: { display: false },
            tooltip: {
                titleFont: estiloDoTextoDoGrafico.font,
                bodyFont: estiloDoTextoDoGrafico.font
            }
        },
        scales: {
            x: {
                ticks: {
                    ...estiloDoTextoDoGrafico,
                    autoSkip: false,
                    maxRotation: 0, // Garante texto sempre horizontal
                    minRotation: 0  // Remove inclinação
                }
            },
            y: {
                beginAtZero: true,
                ticks: estiloDoTextoDoGrafico
            }
        }
    }
});

// 2. Gráfico de presença por UF (Barras Verticais)
new Chart(document.getElementById('presencaTuristaChart'), {
    type: 'bar',
    data: {
        labels: ['SP', 'RJ', 'MG', 'BA', 'RS'],
        datasets: [{
            data: [5000, 3000, 2000, 1500, 1200],
            backgroundColor: '#735900'
        }]
    },
    options: {
        responsive: true,
        maintainAspectRatio: false,
        plugins: {
            legend: { display: false },
            tooltip: {
                titleFont: estiloDoTextoDoGrafico.font,
                bodyFont: estiloDoTextoDoGrafico.font
            }
        },
        scales: {
            x: {
                ticks: {
                    ...estiloDoTextoDoGrafico,
                    maxRotation: 0,
                    minRotation: 0
                }
            },
            y: {
                beginAtZero: true,
                ticks: estiloDoTextoDoGrafico
            }
        }
    }
});

// 3. Gráfico de chegadas de turistas (Linha - Mantendo o estilo ondulado)
new Chart(document.getElementById('chegadasTuristasChart'), {
    type: 'line',
    data: {
        labels: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],
        datasets: [{
            data: [35000, 25000, 50000, 50000, 0, 80000, 35000, 70000, 51000, 20000, 20000, 53000],
            borderColor: 'rgba(247, 202, 37, 1)',
            borderWidth: 2,
            tension: 0.4,
            fill: false
        }]
    },
    options: {
        responsive: true,
        maintainAspectRatio: false,
        plugins: {
            legend: { display: false },
            tooltip: {
                titleFont: estiloDoTextoDoGrafico.font,
                bodyFont: estiloDoTextoDoGrafico.font
            }
        },
        scales: {
            x: {
                ticks: {
                    ...estiloDoTextoDoGrafico,
                    maxRotation: 0,
                    minRotation: 0
                }
            },
            y: {
                beginAtZero: true,
                ticks: estiloDoTextoDoGrafico
            }
        }
    }
});