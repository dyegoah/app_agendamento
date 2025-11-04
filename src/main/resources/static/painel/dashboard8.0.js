const sidebar = document.getElementById("sidebar");
const toggleSidebar = document.getElementById("toggleSidebar");
const btnModo = document.getElementById("btnModo");
const colorPanel = document.getElementById("colorPanel");

// Toggle sidebar
toggleSidebar.addEventListener("click", () => {
  sidebar.classList.toggle("collapsed");
});

// Mobile menu
document.getElementById("mobileMenuBtn").addEventListener("click", () => {
  sidebar.classList.toggle("show");
});

// Paleta de cores
document.querySelectorAll(".color-option").forEach(opt => {
  opt.addEventListener("click", () => {
    const cor = opt.getAttribute("data-color");
    document.documentElement.style.setProperty("--primary-color", cor);
    localStorage.setItem("corPainel", cor);
  });
});

// Aleatória
document.getElementById("btnMudarCor").addEventListener("click", () => {
	// aplica também aos cabeçalhos e cards
		document.querySelectorAll(".card-header, .card").forEach(el => {
		  el.classList.toggle("gradient");
		});
	// 🌈 Botão de Gradiente ao lado do Aleatória
	const btnMudarCor = document.getElementById("btnMudarCor");
	const btnGradiente = document.createElement("button");
	btnGradiente.id = "btnGradiente";
	btnGradiente.textContent = "🌈 Gradiente";
	btnGradiente.className = "btn btn-outline-light btn-sm w-100 mt-2";
	btnGradiente.addEventListener("click", () => {
	  const sidebar = document.getElementById("sidebar");
	  const topbar = document.getElementById("topbar");

	  // aplica gradiente no sidebar e topbar
	  sidebar.classList.toggle("gradient");
	  topbar.classList.toggle("gradient");

	  // aplica também aos cabeçalhos de cards, tabelas e aos próprios cards
	  document.querySelectorAll(".card-header, .card, .table thead").forEach(el => {
	    el.classList.toggle("gradient");
	  });
	});


	
	btnMudarCor.insertAdjacentElement("afterend", btnGradiente);

	const novaCor = "#" + Math.floor(Math.random() * 16777215).toString(16);
  document.documentElement.style.setProperty("--primary-color", novaCor);
  localStorage.setItem("corPainel", novaCor);
});

// Alternar gradiente
const btnGradiente = document.createElement("button");
btnGradiente.textContent = "🌈 Gradiente";
btnGradiente.className = "btn btn-outline-light btn-sm w-100 mt-2";
btnGradiente.addEventListener("click", () => {
  sidebar.classList.toggle("gradient");
  document.getElementById("topbar").classList.toggle("gradient");
});
document.getElementById("colorPanel").appendChild(btnGradiente);


// Restaurar padrão
document.getElementById("btnRestaurar").addEventListener("click", () => {
  document.documentElement.style.setProperty("--primary-color", "#0d6efd");
  sidebar.classList.remove("collapsed");
  localStorage.clear();
  document.getElementById("logoCliente").src = "../imagens/logo-padrao.png";
  document.body.classList.remove("dark-mode");
  alert("Painel restaurado para o padrão!");
});

// Alternar modo claro/escuro
btnModo.addEventListener("click", () => {
  document.body.classList.toggle("dark-mode");
  const icone = btnModo.querySelector("i");
  icone.classList.toggle("bi-sun");
  icone.classList.toggle("bi-moon-stars");

  const modoAtivo = document.body.classList.contains("dark-mode") ? "dark" : "light";
  localStorage.setItem("modoTema", modoAtivo);
});

// Logo personalizada
document.getElementById("inputLogo").addEventListener("change", (e) => {
  const arquivo = e.target.files[0];
  if (arquivo) {
    const leitor = new FileReader();
    leitor.onload = ev => {
      document.getElementById("logoCliente").src = ev.target.result;
      localStorage.setItem("logoCliente", ev.target.result);
    };
    leitor.readAsDataURL(arquivo);
  }
});

// Restaurar preferências
window.addEventListener("load", () => {
  const corSalva = localStorage.getItem("corPainel");
  const logoSalva = localStorage.getItem("logoCliente");
  const modoSalvo = localStorage.getItem("modoTema");

  if (corSalva) document.documentElement.style.setProperty("--primary-color", corSalva);
  if (logoSalva) document.getElementById("logoCliente").src = logoSalva;
  if (modoSalvo === "dark") document.body.classList.add("dark-mode");
});
