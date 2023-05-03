

module.exports = {
  presets: [
    '@vue/cli-plugin-babel/preset'
  ],
  plugins: [
      [
          'prismjs',
        {
          // languages: ['java','prism-go','json']
            languages: ['java', 'go', 'text']
        }
      ]
  ]
}
