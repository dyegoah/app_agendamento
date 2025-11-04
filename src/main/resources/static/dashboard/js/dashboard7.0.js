// Alternar sidebar
const sidebar = document.getElementById("sidebar");
const toggleSidebar = document.getElementById("toggleSidebar");

toggleSidebar.addEventListener("click", () => {
  sidebar.classList.toggle("collapsed");
});

// Mobile
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

// Botão aleatório
document.getElementById("btnMudarCor").addEventListener("click", () => {
  const novaCor = "#" + Math.floor(Math.random() * 16777215).toString(16);
  document.documentElement.style.setProperty("--primary-color", novaCor);
  localStorage.setItem("corPainel", novaCor);
});

// Restaurar padrão
document.getElementById("btnRestaurar").addEventListener("click", () => {
  document.documentElement.style.setProperty("--primary-color", "#0d6efd");
  sidebar.classList.remove("collapsed");
  localStorage.clear();
  document.getElementById("logoCliente").src = "../imagens/logo-padrao.png";
  alert("Painel restaurado para o padrão!");
});

// Logo
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

// Salvar preferências
window.addEventListener("load", () => {
  const corSalva = localStorage.getItem("corPainel");
  const logoSalva = localStorage.getItem("logoCliente");

  if (corSalva) document.documentElement.style.setProperty("--primary-color", corSalva);
  if (logoSalva) document.getElementById("logoCliente").src = logoSalva;
});
